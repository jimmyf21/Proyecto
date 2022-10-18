package sistema.presentation.empleado.empleados;

import sistema.application.Application;
import sistema.logic.Empleado;
import sistema.logic.Service;

import com.itextpdf.io.font.constants.StandardFonts;
import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.colors.Color;
import com.itextpdf.kernel.colors.ColorConstants;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.borders.Border;
import com.itextpdf.layout.element.*;
import com.itextpdf.layout.properties.HorizontalAlignment;
import com.itextpdf.layout.properties.TextAlignment;
import com.itextpdf.layout.properties.VerticalAlignment;
import sistema.logic.Sucursal;


import java.util.ArrayList;
import java.util.List;

public class Controller {
    private Model model;
    private View view;

    public Controller(View view, Model model) {

        this.model = model;
        this.view = view;

        model.setEmpleados(new ArrayList<>());
        try {
            model.setEmpleados(Service.instance().empleadoAll());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        view.setModel(model);
        view.setController(this);
    }

    public void show(){
        Application.window.setContentPane(view.getPanel());
    }

    public void hide(){

        this.view.setVisible(false);
    }

    public void searchEmpleado(String filtro) throws Exception {
        try {
            List<Empleado> rows = Service.instance().empleadosSearch(filtro);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        model.setEmpleados(Service.instance().empleadosSearch(filtro));
        model.commit();
    }


    public void preAgregar(){
        Application.EMPLEADO_AGREGAR.preAgregar();
    }


    public void editar(int row){
        String cedula = model.getEmpleados().get(row).getCedula();
        Empleado empleado=null;
        try {
            empleado= Service.instance().empleadoGet(cedula);
            Application.EMPLEADO_AGREGAR.editar(empleado);
            Service.instance().store();
        } catch (Exception ex) {}
    }

    public void borrarEmpleado(int row){
        Empleado e = model.getEmpleados().get(row);
        try {
            Service.instance().empleadoDelete(e);
            Service.instance().store();
            this.searchEmpleado("");
            this.searchEmpleado("");
        } catch (Exception ex) {}
    }


    private Cell getCell( Paragraph paragraph,TextAlignment alignment,boolean hasBorder) {
        Cell cell = new Cell().add(paragraph);
        cell.setPadding(0);
        cell.setTextAlignment(alignment);
        if(!hasBorder) cell.setBorder(Border.NO_BORDER);
        return cell;
    }

    private Cell getCell( Image image,HorizontalAlignment alignment,boolean hasBorder) {
        Cell cell = new Cell().add(image);
        image.setHorizontalAlignment(alignment);
        cell.setPadding(0);
        if(!hasBorder) cell.setBorder(Border.NO_BORDER);
        return cell;
    }

    public void imprimir()throws Exception{
        String dest="empleados.pdf";
        PdfFont font = PdfFontFactory.createFont(StandardFonts.HELVETICA);
        PdfWriter writer = new PdfWriter(dest);
        PdfDocument pdf = new PdfDocument(writer);

        //Document document = new Document(pdf, PageSize.A4.rotate());
        Document document = new Document(pdf);
        document.setMargins(20, 20, 20, 20);

        Table header = new Table(1);
        header.setWidth(400);
        header.setHorizontalAlignment(HorizontalAlignment.CENTER);
        header.addCell(getCell(new Paragraph("Sistema Integrado SISE: Sucursales y Empleados, PDF DE EMPLEADOS").setFont(font).setBold().setFontSize(20f), TextAlignment.CENTER,false));
        header.addCell(getCell(new Image(ImageDataFactory.create("logo.jpg")), HorizontalAlignment.CENTER,false));
        document.add(header);

        document.add(new Paragraph(""));document.add(new Paragraph(""));

        Color bkg = ColorConstants.RED;
        Color frg= ColorConstants.WHITE;
        Table body = new Table(7);
        body.setWidth(400);
        body.setHorizontalAlignment(HorizontalAlignment.CENTER);
        body.addCell(getCell(new Paragraph("Cedula").setBackgroundColor(bkg).setFontColor(frg),TextAlignment.CENTER,true));
        body.addCell(getCell(new Paragraph("Nombre").setBackgroundColor(bkg).setFontColor(frg),TextAlignment.CENTER,true));
        body.addCell(getCell(new Paragraph("Telefono").setBackgroundColor(bkg).setFontColor(frg),TextAlignment.CENTER,true));
        body.addCell(getCell(new Paragraph("Salario").setBackgroundColor(bkg).setFontColor(frg),TextAlignment.CENTER,true));
        body.addCell(getCell(new Paragraph("Sucursal").setBackgroundColor(bkg).setFontColor(frg),TextAlignment.CENTER,true));
        body.addCell(getCell(new Paragraph("%Zonaje").setBackgroundColor(bkg).setFontColor(frg),TextAlignment.CENTER,true));
        body.addCell(getCell(new Paragraph("Sal. Total").setBackgroundColor(bkg).setFontColor(frg),TextAlignment.CENTER,true));

        for(Empleado e: model.getEmpleados()){
            body.addCell(getCell(new Paragraph(e.getCedula()),TextAlignment.CENTER,true));
            body.addCell(getCell(new Paragraph(e.getNombre()),TextAlignment.CENTER,true));
            body.addCell(getCell(new Paragraph(e.getTelefono()),TextAlignment.CENTER,true));
            body.addCell(getCell(new Paragraph(e.getSalario()+""),TextAlignment.CENTER,true));
            body.addCell(getCell(new Paragraph(e.getSucursal().getReferencia()),TextAlignment.CENTER,true));
            body.addCell(getCell(new Paragraph(e.getSucursal().getZonaje()+""),TextAlignment.CENTER,true));
            body.addCell(getCell(new Paragraph(e.calSalTotal()+""),TextAlignment.CENTER,true));

        }
        document.add(body);
        document.close();
    }


}
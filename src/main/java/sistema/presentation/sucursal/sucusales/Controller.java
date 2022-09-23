package sistema.presentation.sucursal.sucusales;

import sistema.application.Application;
import sistema.logic.Empleado;
import sistema.logic.Service;
import sistema.logic.Sucursal;

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

import java.util.ArrayList;
import java.util.List;

import java.util.List;

public class Controller {
    private Model model;
    private View view;

    public Controller(View view, Model model) {

        this.model = model;
        this.view = view;

        model.setSucursales(new ArrayList<>());
        model.setSucursales(Service.instance().sucursalAll());

        view.setModel(model);
        view.setController(this);
    }

    public void show(){
        Application.window.setContentPane(view.getPanel());
    }

    public void hide(){
        this.view.setVisible(false);
    }


    public void searchSucursal(String filtro){
        List<Sucursal> rows = Service.instance().sucursalesSearch(filtro);
        model.setSucursales(rows);
        model.commit();
    }

    public void editar(int row){
        String referencia = model.getSucursales().get(row).getReferencia();
        Sucursal sucursal=null;
        try {
            sucursal= Service.instance().sucursalGet(referencia);
            Application.SUCURSAL_AGREGAR.SucursalEdit(sucursal);
        } catch (Exception ex) {}
    }

    public void borrarSucursal(int row){
        Sucursal s = model.getSucursales().get(row);
        try {
            Service.instance().sucursalDelete(s);
            this.searchSucursal("");
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
        String dest="sucursales.pdf";
        PdfFont font = PdfFontFactory.createFont(StandardFonts.HELVETICA);
        PdfWriter writer = new PdfWriter(dest);
        PdfDocument pdf = new PdfDocument(writer);

        //Document document = new Document(pdf, PageSize.A4.rotate());
        Document document = new Document(pdf);
        document.setMargins(20, 20, 20, 20);

        Table header = new Table(1);
        header.setWidth(400);
        header.setHorizontalAlignment(HorizontalAlignment.CENTER);
        header.addCell(getCell(new Paragraph("Sistema Integrado SISE: Sucursales y Empleados").setFont(font).setBold().setFontSize(20f), TextAlignment.CENTER,false));
        header.addCell(getCell(new Image(ImageDataFactory.create("logo.jpg")), HorizontalAlignment.CENTER,false));
        document.add(header);

        document.add(new Paragraph(""));document.add(new Paragraph(""));

        Color bkg = ColorConstants.RED;
        Color frg= ColorConstants.WHITE;
        Table body = new Table(4);
        body.setWidth(400);
        body.setHorizontalAlignment(HorizontalAlignment.CENTER);
        body.addCell(getCell(new Paragraph("Codigo").setBackgroundColor(bkg).setFontColor(frg),TextAlignment.CENTER,true));
        body.addCell(getCell(new Paragraph("Referencia").setBackgroundColor(bkg).setFontColor(frg),TextAlignment.CENTER,true));
        body.addCell(getCell(new Paragraph("Direccion").setBackgroundColor(bkg).setFontColor(frg),TextAlignment.CENTER,true));
        body.addCell(getCell(new Paragraph("Zonaje").setBackgroundColor(bkg).setFontColor(frg),TextAlignment.CENTER,true));
        for(Sucursal e: model.getSucursales()){
            body.addCell(getCell(new Paragraph(e.getCodigo()),TextAlignment.CENTER,true));
            body.addCell(getCell(new Paragraph(e.getReferencia()),TextAlignment.CENTER,true));
            body.addCell(getCell(new Paragraph(e.getDireccion()),TextAlignment.CENTER,true));
            body.addCell(getCell(new Paragraph(e.getZonaje()+""),TextAlignment.CENTER,true));
        }
        document.add(body);
        document.close();
    }


    public void sucursalesAgregar(){
        Application.SUCURSAL_AGREGAR.show();
    }

}
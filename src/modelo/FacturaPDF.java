
package modelo;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import java.io.FileOutputStream;
import java.util.List;
/**
 *
 * @author MGamero
 */
public class FacturaPDF {
    public static void generarFactura(Boleta boleta, Cliente cliente, Vendedor vendedor, List<DetalleBoleta> detalles, String rutaArchivo) throws Exception {
        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream(rutaArchivo));
        document.open();

        // Título
        Font tituloFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 18);
        Paragraph titulo = new Paragraph("Factura N° " + boleta.getNum_bol(), tituloFont);
        titulo.setAlignment(Element.ALIGN_CENTER);
        document.add(titulo);

        document.add(new Paragraph("Fecha: " + boleta.getFec_bol()));
        document.add(new Paragraph("Cliente: " + cliente.getNom_cli()));
        document.add(new Paragraph("Vendedor: " + vendedor.getNom_ven()));
        document.add(Chunk.NEWLINE);

        // Tabla productos
        PdfPTable tabla = new PdfPTable(6);
        tabla.setWidthPercentage(100);
        tabla.setWidths(new float[]{2, 6, 2, 3, 2, 3});

        // Cabeceras
        tabla.addCell("Código");
        tabla.addCell("Producto");
        tabla.addCell("Cantidad");
        tabla.addCell("Precio Unitario");
        tabla.addCell("Descuento");
        tabla.addCell("Subtotal");

        // Rellenar datos de detalles
        for (DetalleBoleta detalle : detalles) {
            tabla.addCell(detalle.getCod_pro());
            // Aquí deberías obtener el nombre y precio desde otro lugar, ejemplo genérico:
            tabla.addCell(detalle.getNombreProducto()); // Implementa este getter o pásalo a detalle
            tabla.addCell(String.valueOf(detalle.getCan()));
            tabla.addCell(String.format("%.2f", detalle.getPrecioUnitario())); // Igual, debes tenerlo acceso
            tabla.addCell(detalle.getDescuento() + "%");
            tabla.addCell(String.format("%.2f", detalle.getSubtotal()));
        }

        document.add(tabla);

        document.add(Chunk.NEWLINE);

        // Total
        Paragraph total = new Paragraph("Total: S/ " + boleta.getTotal_bol(), tituloFont);
        total.setAlignment(Element.ALIGN_RIGHT);
        document.add(total);

        document.close();
    }
}

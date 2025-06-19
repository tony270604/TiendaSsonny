
package modelo;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.List;
/**
 *
 * @author MGamero
 */
public class FacturaPDF {
    public static void generarFactura(Boleta boleta, Cliente cliente, Vendedor vendedor, List<DetalleBoleta> detalles, String nombreArchivo) throws Exception {
        
        
        // Define la ruta donde se guardará el PDF
        String ruta = "C:\\Users\\ASUS\\Documents\\" + nombreArchivo; // Cambia esto a la ruta deseada
        // Crear la carpeta si no existe
        File carpeta = new File("C:\\Users\\ASUS\\Documents\\TiendaSonny_boletasPDF");
        if (!carpeta.exists()) {
            carpeta.mkdirs(); // Crea la carpeta (y padres si es necesario)
        }
        
        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream(ruta));
        document.open();
        // Título
        Font tituloFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 18);
        Paragraph titulo = new Paragraph("Factura N° " + String.format("%08d", boleta.getNum_bol()), tituloFont);
        titulo.setAlignment(Element.ALIGN_CENTER);
        document.add(titulo);
        
        // Formatear la fecha
        SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        String fechaFormateada = formatoFecha.format(boleta.getFec_bol());

        document.add(new Paragraph("Fecha: " + fechaFormateada));
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

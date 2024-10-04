import org.w3c.dom.DOMImplementation
import org.w3c.dom.Document
import org.w3c.dom.Element
import org.w3c.dom.Text
import java.io.BufferedReader
import java.nio.file.Files
import java.nio.file.Path
import javax.xml.parsers.DocumentBuilderFactory
import javax.xml.transform.OutputKeys
import javax.xml.transform.Source
import javax.xml.transform.Transformer
import javax.xml.transform.TransformerFactory
import javax.xml.transform.dom.DOMSource
import javax.xml.transform.stream.StreamResult

/**
 * # Data Class Empleado
 *
 * Define los parámetro que ha de tener un empleado
 */
data class Empleado(
    val id: Int,
    val apellido: String,
    val departmento: String,
    val salario: Double,
)

/**
 * # Funcion Leer Empleados
 *
 * Esta función consigue leer del archivo csv todos los empleados y los pasa a una lista de Objetos Empleaod
 *
 * @return MutableList<Empleados>: Devuelve una lista de Empleados(Objeto)
 */
fun leerEmpleados(): MutableList<Empleado> {
    val listaEmpleados = mutableListOf<Empleado>()
    val lista = mutableListOf<String>()

    val archivePath = Path.of("src/main/resources/empleados.csv")
    val br: BufferedReader = Files.newBufferedReader(archivePath)
    br.lines().forEach { line -> lista.add(line) }

    for (linea in lista) {
        // Hay que quitar la primera linea
        if (linea.split(",")[0].toString() == "ID") {
            // Solo para quitar la primera linea
        }
        else{
            // A través de cada linea podemos asignar a un objeto empleado cada atributo de forma separada y añadirlo a la lista de empleados
            val empleado = Empleado(linea.split(",")[0].toInt(), linea.split(",")[1], linea.split(",")[2], linea.split(",")[3].toDouble())
            listaEmpleados.add(empleado)
        }
    }
    return listaEmpleados
}

fun EmpleadosXML(listaEmpleados: MutableList<Empleado>) {
    val dbf = DocumentBuilderFactory.newInstance()
    val builder = dbf.newDocumentBuilder()
    val imp: DOMImplementation = builder.domImplementation
    val document: Document = imp.createDocument(null, "empleados", null)

    for (empleadoLista in listaEmpleados) {
        val empleado: Element = document.createElement("empleado")
        empleado.setAttribute("id", empleadoLista.id.toString())
        document.documentElement.appendChild(empleado)

        val apellido: Element = document.createElement("apellido")
        val departamento: Element = document.createElement("departamento")
        val salario: Element = document.createElement("salario")

        val textContentApellido: Text = document.createTextNode(empleadoLista.apellido)
        val textContentDepartamento: Text = document.createTextNode(empleadoLista.departmento)
        val textContentSalario: Text = document.createTextNode(empleadoLista.salario.toString())

        apellido.appendChild(textContentApellido)
        departamento.appendChild(textContentDepartamento)
        salario.appendChild(textContentSalario)

        empleado.appendChild(apellido)
        empleado.appendChild(departamento)
        empleado.appendChild(salario)

        val source: Source = DOMSource(document)
        val result: StreamResult = StreamResult(Path.of("src/main/resources/Output/empleadosSalida.xml").toFile())
        val transformer: Transformer = TransformerFactory.newInstance().newTransformer()

        transformer.setOutputProperty(OutputKeys.INDENT, "yes")
        transformer.transform(source, result)
    }
}

fun modificarSueldo() {

}
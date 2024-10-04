import java.io.BufferedReader
import java.nio.file.Files
import java.nio.file.Path

/**
 * # Data Class Empleado
 *
 * Define los parámetro que ha de tener un empleado
 */
data class Empleado(
    val id: Int,
    val apellido: String,
    val department: String,
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
            // A traves de cada linea podemos asignar a un objeto empleado cada atributo de forma separada y añadirlo a la lista de empleados
            val empleado = Empleado(linea.split(",")[0].toInt(), linea.split(",")[1], linea.split(",")[2], linea.split(",")[3].toDouble())
            listaEmpleados.add(empleado)
        }
    }
    return listaEmpleados
}
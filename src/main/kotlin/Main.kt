fun main() {
    var listaEmpleados = leerEmpleados()
    EmpleadosXML(listaEmpleados)
    modificarSueldo(listaEmpleados)
    EmpleadosXML(listaEmpleados)
}
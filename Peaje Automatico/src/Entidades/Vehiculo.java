package Entidades;

/**
 *
 * @author Agustín Picos
 */
public class Vehiculo {
    private boolean unidad_especial;
    private String tipo;
    private boolean genera_accidente;
    private long horaEntrada;
    private long horaSalida;
    private String matricula;
    
    public Vehiculo(String linea){
        String[]datos = linea.split(",");
        this.matricula = datos[0];
        this.tipo = datos[1];
        this.unidad_especial = Boolean.valueOf(datos[2]);
        this.genera_accidente = Boolean.valueOf(datos[3]);
    }
    public String pasar_a_String(){
        String dato="";
        dato+=String.valueOf(matricula)+",";
        dato+=tipo+",";
        dato+=String.valueOf(unidad_especial)+",";
        dato+=String.valueOf(genera_accidente)+",";
        dato+=String.valueOf(horaEntrada)+",";
        dato+=String.valueOf(horaSalida)+",";
        dato+=String.valueOf(horaSalida-horaEntrada);
        return dato;
    }

    public boolean isUnidad_especial() {
        return unidad_especial;
    }

    public String getTipo() {
        return tipo;
    }

    public void setHoraEntrada(long horaEntrada) {
        this.horaEntrada = horaEntrada;
    }

    public void setHoraSalida(long horaSalida) {
        this.horaSalida = horaSalida;
    }

}

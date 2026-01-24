public class RadioKM implements Radio{
   private boolean encendida;
   private boolean esFM;
   private double estacionActual;
   private double[] presetsFM = new double[12];
   private double[] presetsAM = new double[12];


   public RadioKM(){
    this.encendida = false;
    this.esFM = true;
    this.estacionActual = 87.9;
    this.presetsFM = new double[12];
    this.presetsAM = new double[12];
    }


   public boolean isFM() {
    return this.esFM;
    }

   public double getEstacionActual(){
    return this.estacionActual;
   }

    public boolean isEncendida(){
     return this.encendida;
    }

   @Override
   public void encenderRadio() {
    this.encendida = true;
   }

   @Override 
   public void apagarRadio(){
    this.encendida = false;
   }

   @Override
   public void avanzarEstacion(){
    if(this.encendida){
        if(this.esFM){
            this.estacionActual += 0.2;
            if(this.estacionActual > 107.9){
                this.estacionActual = 87.9;
            }
        } else {
            this.estacionActual += 10;
            if(this.estacionActual > 1610){
                this.estacionActual = 530;
            }
        }
    }
   }

   @Override
   public void cambiarFM(){
    if(this.encendida){
        this.esFM = true;
        this.estacionActual = 87.9;
    }
   }

   @Override
   public void cambiarAM(){
    if(this.encendida){
        this.esFM = false;
        this.estacionActual = 530;
    }
   }

    @Override
    public void guardarEstacion(int numeroBoton){
    if(this.encendida && numeroBoton >= 1 && numeroBoton <=12){
        if (esFM) {
            presetsFM[numeroBoton - 1] = estacionActual;
        } else {
            presetsAM[numeroBoton - 1] = estacionActual;
        }
     }
    }


    @Override
    public void cargarEstacion(int numeroBoton){
    if(this.encendida && numeroBoton >= 1 && numeroBoton <=12){
        if (esFM) {
            estacionActual = presetsFM[numeroBoton - 1];
        } else {
            estacionActual = presetsAM[numeroBoton - 1];
        }
     }
    }
}
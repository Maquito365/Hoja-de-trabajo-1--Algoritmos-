public class RadioKM implements Radio{
   private boolean encendida;
   private boolean esFM;
   private double estacionActual;
   private double[] estacionesGuardadas;

   public RadioKM(){
    this.encendida = false;
    this.esFM = true; 
    this.estacionActual = 87.9;  
    this.estacionesGuardadas = new double[12];
   }

   public double getEstacionActual(){
    return this.estacionActual;
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

   @Override
   public void cambiarFM(){
    this.esFM = true;
    this.estacionActual = 87.9;
   }

   @Override
   public void cambiarAM(){
    this.esFM = false;
    this.estacionActual = 530;
   }

}
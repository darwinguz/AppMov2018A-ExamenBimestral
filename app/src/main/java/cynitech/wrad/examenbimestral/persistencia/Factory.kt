package cynitech.wrad.examenbimestral.persistencia

import cynitech.wrad.examenbimestral.beans.BeaComida
import java.util.*

class Factory {
    companion object {
        var comidas: ArrayList<BeaComida> = ArrayList()

        init {
            comidas.add(BeaComida(1,"Seco de Pollo", "Arroz con bistec de pollo","Ecuatoriana",1,false, null))
            comidas.add(BeaComida(2,"Seco de Chivo", "Arroz con bistec de chivo","Ecuatoriana",1,false, null))
            comidas.add(BeaComida(3,"Camarones Apanados", "Camarones fritos en harina de trigo","Ecuatoriana",1,false, null))
            comidas.add(BeaComida(4,"Ceviche de Pollo", "Cebollas con pollo","Ecuatoriana",1,false, null))

        }

    }
}
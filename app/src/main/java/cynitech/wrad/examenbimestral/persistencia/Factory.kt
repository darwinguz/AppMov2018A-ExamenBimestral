package cynitech.wrad.examenbimestral.persistencia

import cynitech.wrad.examenbimestral.modelos.ModComida
import java.util.*

class Factory {
    companion object {
        var comidas: ArrayList<ModComida> = ArrayList()

        init {
            comidas.add(ModComida(1,"Seco de Pollo", "Arroz con bistec de pollo","Ecuatoriana",1,false, null))
            comidas.add(ModComida(2,"Seco de Chivo", "Arroz con bistec de chivo","Ecuatoriana",1,false, null))
            comidas.add(ModComida(3,"Camarones Apanados", "Camarones fritos en harina de trigo","Ecuatoriana",1,false, null))
            comidas.add(ModComida(4,"Ceviche de Pollo", "Cebollas con pollo","Ecuatoriana",1,false, null))

        }

    }
}
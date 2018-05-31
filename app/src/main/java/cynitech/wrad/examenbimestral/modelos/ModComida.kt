package cynitech.wrad.examenbimestral.modelos

import android.os.Parcel
import android.os.Parcelable

class ModComida(val id: Int?,
                val nombrePlato: String,
                val descripcionPlato: String,
                val nacionalidad: String,
                val numeroPersonas: Int,
                val picante: Boolean,
                val ingredientes: List<ModIngrediente>?
) : Parcelable {
    constructor(parcel: Parcel) : this(
            parcel.readInt(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readInt(),
            parcel.readByte() != 0.toByte(),
            parcel.createTypedArrayList(ModIngrediente)) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        if (id != null) {
            parcel.writeInt(id)
        }
        parcel.writeString(nombrePlato)
        parcel.writeString(descripcionPlato)
        parcel.writeString(nacionalidad)
        parcel.writeInt(numeroPersonas)
        parcel.writeByte(if (picante) 1 else 0)
        parcel.writeTypedList(ingredientes)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ModComida> {
        override fun createFromParcel(parcel: Parcel): ModComida {
            return ModComida(parcel)
        }

        override fun newArray(size: Int): Array<ModComida?> {
            return arrayOfNulls(size)
        }
    }

    override fun toString(): String {
        return "Comida: Nombre=$nombrePlato Descripcion=$descripcionPlato Nacionalidad=$nacionalidad NumeroDePersonas=$numeroPersonas Picante=$picante"
    }
}
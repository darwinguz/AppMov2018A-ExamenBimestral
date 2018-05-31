package cynitech.wrad.examenbimestral.modelos

import android.os.Parcel
import android.os.Parcelable

class ModIngrediente(val id: Int?,
                     val nombreIngrediente: String,
                     val cantidad: Int,
                     val descripcionPreparacion: String,
                     val opcional: Boolean,
                     val tipoIngrediente: String,
                     val necesitaRefrigeracion: Boolean,
                     val comidaId: Int
) : Parcelable {
    constructor(parcel: Parcel) : this(
            parcel.readValue(Int::class.java.classLoader) as? Int,
            parcel.readString(),
            parcel.readInt(),
            parcel.readString(),
            parcel.readByte() != 0.toByte(),
            parcel.readString(),
            parcel.readByte() != 0.toByte(),
            parcel.readInt()) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeValue(id)
        parcel.writeString(nombreIngrediente)
        parcel.writeInt(cantidad)
        parcel.writeString(descripcionPreparacion)
        parcel.writeByte(if (opcional) 1 else 0)
        parcel.writeString(tipoIngrediente)
        parcel.writeByte(if (necesitaRefrigeracion) 1 else 0)
        parcel.writeInt(comidaId)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ModIngrediente> {
        override fun createFromParcel(parcel: Parcel): ModIngrediente {
            return ModIngrediente(parcel)
        }

        override fun newArray(size: Int): Array<ModIngrediente?> {
            return arrayOfNulls(size)
        }
    }


}




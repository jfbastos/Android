package br.com.iesb.maximaapp.model.database

import androidx.room.TypeConverter
import br.com.iesb.maximaapp.model.Contato
import com.google.gson.reflect.TypeToken

import com.google.gson.Gson
import java.lang.reflect.Type
import java.util.*


class ConversorContatos {

    private var gson = Gson()

    @TypeConverter
    fun stringToSomeObjectList(data: String?): List<Contato?>? {
        if (data == null) {
            return Collections.emptyList()
        }
        val listType: Type = object : TypeToken<List<Contato?>?>() {}.type
        return gson.fromJson<List<Contato?>>(data, listType)
    }

    @TypeConverter
    fun someObjectListToString(someObjects: List<Contato?>?): String? {
        return gson.toJson(someObjects)
    }
}
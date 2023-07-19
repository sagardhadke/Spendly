package net.uniquecomputer.spendly.Model

import io.realm.RealmObject
import java.util.Date
import io.realm.annotations.PrimaryKey

class RecordsModel (val type : String , var amount : Double,var category : String , var account : String , var note : String, var date : Date, @PrimaryKey var id : Long ) : RealmObject() {


}
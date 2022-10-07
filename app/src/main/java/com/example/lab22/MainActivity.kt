package com.example.lab22

import android.content.res.XmlResourceParser
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.CompoundButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    var caloriesTotal: Int = 0
    var carbsTotal: Int = 0
    var cholesterolTotal: Int = 0
    var fatTotal: Double = 0.0
    var fiberTotal: Double = 0.0
    var proteinTotal: Int = 0
    var servingTotal: Double = 0.0

    val myList = arrayListOf<TacoIngredient>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Widgets
        val tv_serving = findViewById<TextView>(R.id.tv_serving)
        val tv_calories = findViewById<TextView>(R.id.tv_calories)
        val tv_carbs = findViewById<TextView>(R.id.tv_carbs)
        val tv_cholesterol = findViewById<TextView>(R.id.tv_cholesterol)
        val tv_fat = findViewById<TextView>(R.id.tv_fat)
        val tv_fiber = findViewById<TextView>(R.id.tv_fiber)
        val tv_protein = findViewById<TextView>(R.id.tv_protein)
        val btnTotal = findViewById<Button>(R.id.btnTotal)

        // Adding checkboxes
        val checkBoxList = arrayListOf<CheckBox>()
        checkBoxList.add(findViewById(R.id.cbIng0))
        checkBoxList.add(findViewById(R.id.cbIng1))
        checkBoxList.add(findViewById(R.id.cbIng2))
        checkBoxList.add(findViewById(R.id.cbIng3))
        checkBoxList.add(findViewById(R.id.cbIng4))
        checkBoxList.add(findViewById(R.id.cbIng5))
        checkBoxList.add(findViewById(R.id.cbIng6))
        checkBoxList.add(findViewById(R.id.cbIng7))
        checkBoxList.add(findViewById(R.id.cbIng8))
        checkBoxList.add(findViewById(R.id.cbIng9))
        for (i in 0 until checkBoxList.size) {
            checkBoxList[i].setOnCheckedChangeListener { buttonView, isChecked ->
                if (checkBoxList[i].isChecked) {
                    caloriesTotal += myList.get(i).calories
                    carbsTotal += myList.get(i).carbs
                    cholesterolTotal += myList.get(i).cholesterol
                    fatTotal += myList.get(i).fat
                    fiberTotal += myList.get(i).fiber
                    proteinTotal += myList.get(i).protein
                    servingTotal += myList.get(i).serving
                } else {
                    caloriesTotal -= myList.get(i).calories
                    carbsTotal -= myList.get(i).carbs
                    cholesterolTotal -= myList.get(i).cholesterol
                    fatTotal -= myList.get(i).fat
                    fiberTotal -= myList.get(i).fiber
                    proteinTotal -= myList.get(i).protein
                    servingTotal -= myList.get(i).serving
                }
            }
        }

        /*
        Boundaries
Calories	400
Carbs	70
Cholesterol	150
Fat	30
         */

        btnTotal.setOnClickListener {
            var calString = caloriesTotal.toString()
            var carbString = carbsTotal.toString()
            var cholesterolString = cholesterolTotal.toString()
            var fatString = fatTotal.toString()
            if(caloriesTotal >= 400) {
                calString += "*"
            }
           if(carbsTotal >= 70) {
                carbString += "*"
            }
            if(cholesterolTotal >= 150) {
                cholesterolString += "*"
            }
            if(fatTotal >= 30.0) {
                fatString += "*"
            }

            tv_serving.text = servingTotal.toString()
            tv_calories.text = calString
            tv_carbs.text = carbString
            tv_cholesterol.text = cholesterolString
            tv_fat.text = fatString
            tv_fiber.text = fiberTotal.toString()
            tv_protein.text = proteinTotal.toString()
        }

        val parser = resources.getXml(R.xml.taco)
        var eventType = -1
        var myIndex = -1

        while (eventType != XmlResourceParser.END_DOCUMENT) {
            if (eventType == XmlResourceParser.START_TAG) {
                when (parser.name) {
                    "element" -> {
                        myList.add(TacoIngredient())
                        myIndex++
                    }
                    "calories" -> {
                        parser.next()
                        myList[myIndex].calories = parser.text.toInt()
                        parser.next()
                    }
                    "carbs" -> {
                        parser.next()
                        myList[myIndex].carbs = parser.text.toInt()
                        parser.next()
                    }
                    "cholesterol" -> {
                        parser.next()
                        myList[myIndex].cholesterol = parser.text.toInt()
                        parser.next()
                    }
                    "fat" -> {
                        parser.next()
                        myList[myIndex].fat = parser.text.toDouble()
                        parser.next()
                    }
                    "fiber" -> {
                        parser.next()
                        myList[myIndex].fiber = parser.text.toDouble()
                        parser.next()
                    }
                    "name" -> {
                        parser.next()
                        myList[myIndex].name = parser.text.toString()
                        checkBoxList[myIndex].text = myList[myIndex].name
                        parser.next()
                    }
                    "protein" -> {
                        parser.next()
                        myList[myIndex].protein = parser.text.toInt()
                        parser.next()
                    }
                    "serving" -> {
                        parser.next()
                        myList[myIndex].serving = parser.text.toDouble()
                        parser.next()
                    }
                }
            }
            eventType = parser.next()
        }
    }
}
package vn.hust.edu.mylearnapplication

import android.os.Bundle
import android.text.TextWatcher
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.ComponentActivity
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.AdapterView.OnItemSelectedListener
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.ListView
import android.view.LayoutInflater
import android.widget.Spinner
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import vn.hust.edu.mylearnapplication.ui.theme.MyLearnApplicationTheme
import kotlin.math.sqrt

class MainActivity : AppCompatActivity() {
    private lateinit var editName: EditText
    private lateinit var editMSSV: EditText
    private lateinit var btnAdd: Button
    private lateinit var listView: ListView
    private val students = ArrayList<Student>()
    private lateinit var adapter: StudentAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        editName = findViewById(R.id.edit_text_id1)
        editMSSV = findViewById(R.id.edit_text_id2)
        btnAdd = findViewById(R.id.btnAdd)
        listView = findViewById(R.id.list_item1)

        adapter = StudentAdapter()
        listView.adapter = adapter

        btnAdd.setOnClickListener {
            val name = editName.text.toString().trim()
            val mssv = editMSSV.text.toString().trim()
            if (name.isNotEmpty() && mssv.isNotEmpty()) {
                students.add(0, Student(name, mssv)) // Add student to the beginning of the list
                adapter.notifyDataSetChanged()
                editName.text.clear()
                editMSSV.text.clear()
            }
        }
    }

    data class Student(val name: String, val mssv: String)

    inner class StudentAdapter : ArrayAdapter<Student>(this, R.layout.list_item, students) {
        override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
            val view = convertView ?: LayoutInflater.from(context).inflate(R.layout.list_item, parent, false)
            val textView: TextView = view.findViewById(R.id.textView)
            val deleteIcon: ImageView = view.findViewById(R.id.deleteIcon)
            val student = getItem(position)
            textView.text = "${student?.name} - ${student?.mssv}"

            deleteIcon.setOnClickListener {
                students.removeAt(position)
                notifyDataSetChanged()
            }
            return view
        }
    }
}

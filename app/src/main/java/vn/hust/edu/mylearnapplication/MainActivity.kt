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
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import vn.hust.edu.mylearnapplication.ui.theme.MyLearnApplicationTheme
import kotlin.math.sqrt

class MainActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: StudentAdapter
    private val studentList = mutableListOf<Student>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val nameEditText = findViewById<EditText>(R.id.edit_text_id1)
        val mssvEditText = findViewById<EditText>(R.id.edit_text_id2)
        val btnAdd = findViewById<Button>(R.id.btnAdd)
        recyclerView = findViewById(R.id.recycler_view1)

        adapter = StudentAdapter(studentList)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        btnAdd.setOnClickListener {
            val name = nameEditText.text.toString()
            val mssv = mssvEditText.text.toString()
            if (name.isNotEmpty() && mssv.isNotEmpty()) {
                studentList.add(Student(name, mssv))
                adapter.notifyItemInserted(studentList.size - 1)
                nameEditText.text.clear()
                mssvEditText.text.clear()
            }
        }
    }
}

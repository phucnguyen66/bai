package com.example.baitaptrenlop

import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private lateinit var etName: EditText
    private lateinit var rgGender: RadioGroup
    private lateinit var cbAgree: CheckBox
    private lateinit var btnSave: Button
    private lateinit var recyclerView: RecyclerView
    private lateinit var userAdapter: UserAdapter
    private var userList = mutableListOf<User>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Ánh xạ các view
        etName = findViewById(R.id.editTextText)
        rgGender = findViewById(R.id.rgGender)
        cbAgree = findViewById(R.id.checkBox)
        btnSave = findViewById(R.id.button)
        recyclerView = findViewById(R.id.recyclerView)

        // Cài đặt RecyclerView
        userAdapter = UserAdapter(userList)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = userAdapter

        // Xử lý sự kiện nhấn nút Lưu
        btnSave.setOnClickListener {
            val name = etName.text.toString().trim()
            val selectedGenderId = rgGender.checkedRadioButtonId

            if (selectedGenderId != -1) {  // Kiểm tra nếu đã chọn giới tính
                val selectedGender = findViewById<RadioButton>(selectedGenderId)
                val gender = selectedGender.text.toString()

                if (cbAgree.isChecked) {
                    // Thêm thông tin người dùng vào danh sách
                    val user = User(name, gender)
                    userList.add(user)

                    // Cập nhật RecyclerView
                    userAdapter.notifyDataSetChanged()
                } else {
                    Toast.makeText(this, "Bạn phải đồng ý với điều khoản sử dụng", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "Bạn phải chọn giới tính", Toast.LENGTH_SHORT).show()
            }
        }
    }
}

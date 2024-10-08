package com.laboratorio.tp1sharedpreferences.Registrar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.laboratorio.tp1sharedpreferences.MainActivity;
import com.laboratorio.tp1sharedpreferences.Model.Usuario;
import com.laboratorio.tp1sharedpreferences.databinding.ActivityRegistrarBinding;

public class RegistrarActivity extends AppCompatActivity {
    private ActivityRegistrarBinding binding;
    private RegistrarViewModel vm;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRegistrarBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //mostrar datos
        Intent intent = getIntent();
        Long dni = intent.getLongExtra("dni", -1);
        String apellido = intent.getStringExtra("apellido");
        String nombre = intent.getStringExtra("nombre");
        String mail = intent.getStringExtra("mail");
        String password = intent.getStringExtra("password");

        binding.etDni.setText(String.valueOf(dni));
        binding.etApellido.setText(apellido);
        binding.etNombre.setText(nombre);
        binding.etEmail.setText(mail);
        binding.etPassword.setText(password);


        vm = ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication()).create(RegistrarViewModel.class);
        vm.getMMsj().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                Toast.makeText(RegistrarActivity.this, s, Toast.LENGTH_SHORT).show();
                if (s.equals("Registro exitoso!!")) {
                    // Redirigir al MainActivity
                    Intent intent = new Intent(RegistrarActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish(); // Cierra la actividad de registro
                }
            }
        });

        binding.btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Long dni = Long.parseLong(binding.etDni.getText().toString());
                String apellido = binding.etApellido.getText().toString();
                String nombre = binding.etNombre.getText().toString();
                String mail = binding.etEmail.getText().toString();
                String password = binding.etPassword.getText().toString();

                Usuario usuario = new Usuario(dni, apellido, nombre, mail, password);
                vm.registrar(usuario);
            }
        });

        binding.btnVolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RegistrarActivity.this, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK); // Limpiar la pila de actividades
                startActivity(intent);
                finish();
            }
        });
    }
}

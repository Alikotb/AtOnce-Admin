package com.example.atonce_admin.presentation.login.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.*
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.atonce_admin.presentation.theme.MediumFont
import com.example.atonce_admin.presentation.theme.PrimaryColor
import com.example.atonce_admin.presentation.theme.RegularFont
import com.example.atonce_admin.presentation.theme.TextFieldColor

@Composable
fun CustomTextField(
    label: String,
    value: String,
    onValueChange: (String) -> Unit,
    isPassword: Boolean = false
) {
    var passwordVisible by remember { mutableStateOf(false) }

    Column(modifier = Modifier.fillMaxWidth()) {
        Text(
            label,
            fontSize = 14.sp,
            fontFamily = MediumFont,
            modifier = Modifier.padding(bottom = 4.dp)
        )

        OutlinedTextField(
            value = value,
            placeholder = { Text(label, fontSize = 12.sp,
                color = Color.Gray
                , fontFamily = RegularFont
            ) },
            onValueChange = onValueChange,
            singleLine = true,
            textStyle = LocalTextStyle.current.copy(fontSize = 16.sp),
            visualTransformation = if (isPassword && !passwordVisible)
                PasswordVisualTransformation()
            else
                VisualTransformation.None,
            trailingIcon = {
                if (isPassword) {
                    IconButton(onClick = { passwordVisible = !passwordVisible }) {
                        Icon(
                            imageVector = if (!passwordVisible) Icons.Default.Visibility else Icons.Default.VisibilityOff,
                            contentDescription = null
                        )
                    }
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
            keyboardOptions = if (isPassword)
                KeyboardOptions(keyboardType = KeyboardType.Password)
            else
                KeyboardOptions.Default,
            shape = RoundedCornerShape(8.dp),
            colors = OutlinedTextFieldDefaults.colors(
                unfocusedBorderColor = Color.Transparent,
                focusedBorderColor = PrimaryColor,
                disabledBorderColor = Color.Transparent,
                errorBorderColor = Color.Transparent,
                cursorColor = Color.Black,
                focusedContainerColor = Color(0xFFF5F5F5),
                unfocusedContainerColor = TextFieldColor,
                disabledContainerColor = Color(0xFFF5F5F5),
                errorContainerColor = Color(0xFFF5F5F5)
            )
        )
    }
}


@Preview(showBackground = true)
@Composable
fun CustomTextFieldPreview() {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    CustomTextField(label = "Email", value = email, onValueChange = { email = it })
}

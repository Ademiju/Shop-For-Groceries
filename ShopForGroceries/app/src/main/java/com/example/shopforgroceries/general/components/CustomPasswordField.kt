package com.example.shopforgroceries.general.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusState
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import com.example.shopforgroceries.R

@Composable
fun PasswordInputField(
    value: String,
    onValueChange: (String) -> Unit, // change when value of input field changes
    onFocusChange: (FocusState) -> Unit,   //change when the form input focus changes
    modifier: Modifier,
    headerText: String,
    hasError: Boolean,
    errorMessage: String,
    imeAction: ImeAction = ImeAction.Next,
){
    val touched = remember{             //validates the form input fields only after the fields have been interacted with
        mutableStateOf(false)
    }
    val  passwordVisible = remember {
        mutableStateOf(false)
    }
    Column(modifier = modifier.fillMaxWidth()) {
        OutlinedTextField(
            value = value,
            onValueChange = {
                touched.value = true
                onValueChange(it)
            },
            modifier = Modifier.onFocusChanged {
                if (touched.value) onFocusChange(it)
            },
            label = {
                Text(text = headerText)
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password
                ,
                imeAction = imeAction
            ),
            trailingIcon = {
                IconButton(onClick = {passwordVisible.value = !passwordVisible.value}) {
                    Icon(painter = painterResource(
                        id = if (passwordVisible.value) {
                            R.drawable.visibility
                        }else{
                            R.drawable.visibility_off
                        }
                    ), contentDescription = "")

                }
            },
            visualTransformation = if (passwordVisible.value){
                VisualTransformation.None

            }else{
                 PasswordVisualTransformation()
                 },
        )
        if (hasError){
            Text(text = errorMessage)
        }
    }

}
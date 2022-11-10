package com.capstone.artwhale.presentation.common

import android.content.Context
import android.util.AttributeSet
import android.util.Base64
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.DataBindingUtil
import com.capstone.artwhale.R
import com.capstone.artwhale.databinding.ViewToolBarCustomBinding
import java.io.ByteArrayInputStream
import java.io.ObjectInputStream

class CustomToolBar(
    context: Context,
    attrs: AttributeSet?
) : ConstraintLayout(context, attrs) {

    private val binding: ViewToolBarCustomBinding =
        DataBindingUtil.inflate(
            LayoutInflater.from(context),
            R.layout.view_tool_bar_custom,
            this,
            true
        )
    private val currentMode: Int

    init {
        val a = context.obtainStyledAttributes(attrs, R.styleable.CustomToolBar)
        currentMode = a.getInt(R.styleable.CustomToolBar_toolBarType, 0)

        with(binding) {
            tvTitle.text = a.getString(R.styleable.CustomToolBar_toolbarTitle).toString()

            ivDefault.setOnClickListener {
                val callBackStr = a.getString(R.styleable.CustomToolBar_onClickBackIcon)
                    ?: return@setOnClickListener
                createCallBackFunc(callBackStr)()
            }
            ivAdditional.setOnClickListener {
                val callBackStr = a.getString(R.styleable.CustomToolBar_onClickUserIcon)
                    ?: return@setOnClickListener
                createCallBackFunc(callBackStr)()
            }
        }
        a.recycle()
    }

    fun setOnClickDefaultIcon(c: () -> Unit) = binding.ivDefault.setOnClickListener { c() }
    fun setOnClickAdditionalIcon(c: () -> Unit) = binding.ivAdditional.setOnClickListener { c() }

    fun setAppBarTitle(title: String) {
        binding.tvTitle.text = title
    }

    private fun createCallBackFunc(callBackStr: String): () -> Unit {
        val decoded = Base64.decode(callBackStr, Base64.DEFAULT)

        return ObjectInputStream(ByteArrayInputStream(decoded))
            .readObject() as (() -> Unit)
    }
}
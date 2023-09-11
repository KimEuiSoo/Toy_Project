package com.example.register.Model

data class RegisterModel(
    var b_no : String,
    var b_stt : String,
    var b_stt_cd : String,
    var tax_type : String,
    var tax_type_cd : String,
    var end_dt : String,
    var utcc_yn : String,
    var tax_type_change_dt : String,
    var invoice_apply_dt : String,
    var rbf_tax_type : String,
    var rbf_tax_type_cd : String
)


data class ResponseModel(
    var status_code : String,
    var match_cnt : String,
    var request_cnt : String,
    var data : List<RegisterModel>
)

package com.example.bloodbank.Utils

object Endpoints {

    private const val base_url = "https://busy-programmer.000webhostapp.com/"

    const val register_url = "${base_url}register.php"
    const val login_url = "${base_url}login.php"
    const val upload_request = "${base_url}upload_request.php"
    const val get_requests = "${base_url}get_requests.php"
    const val search_donors = "${base_url}search_donors.php"
}

Responsi 1 Mobile - Charlton Athletic FC

Nama: Ramadhan Fakhtur Rakhman
NIM: H1D023010

Shift Lama: D
Shift Baru: E
Klub: Charlton Athletic FC
Mata Kuliah: Pemrograman Mobile A
Responsi 1 Praktikum Pemograman Mobile

Deskripsi Aplikasi

Aplikasi “Responsi 1 Mobile – Charlton Athletic FC” merupakan aplikasi Android yang dirancang untuk menampilkan informasi klub sepak bola Charlton Athletic FC secara real-time dan interaktif. Aplikasi ini dikembangkan menggunakan bahasa Kotlin dengan pendekatan modern Android development, yang mengutamakan efisiensi, kecepatan respons, serta tampilan antarmuka pengguna (UI) yang intuitif dan menarik.

Tujuan utama aplikasi ini adalah memberikan kemudahan bagi pengguna untuk memperoleh data terbaru seputar klub Charlton Athletic FC, termasuk profil klub, data pelatih utama, serta daftar lengkap pemain (squad). Semua data diperoleh langsung dari Football-Data.org API, sebuah sumber resmi yang menyediakan data sepak bola dunia secara komprehensif dan terverifikasi.

Dalam pembangunannya, aplikasi ini memanfaatkan Retrofit sebagai library utama untuk komunikasi jaringan (network request), serta GsonConverterFactory untuk melakukan konversi data dari format JSON ke objek Kotlin secara otomatis. Selain itu, seluruh proses pengambilan data dilakukan secara asynchronous menggunakan Kotlin Coroutines, agar kinerja aplikasi tetap ringan dan tidak mengganggu kenyamanan pengguna saat berinteraksi dengan antarmuka.

Aplikasi ini terdiri dari beberapa halaman utama yang saling terintegrasi, antara lain:

MainActivity berfungsi sebagai halaman beranda yang menampilkan nama dan logo klub, sekaligus menyediakan navigasi menuju halaman lainnya.

HalamanClubActivity menyajikan informasi umum dan sejarah klub dalam format teks deskriptif.

HalamanCoachActivity menampilkan data lengkap mengenai pelatih utama, seperti nama, negara asal, dan tanggal lahir, disertai efek animasi fade-in untuk meningkatkan pengalaman visual pengguna.

HalamanTeamActivity menampilkan daftar lengkap pemain Charlton Athletic FC dalam bentuk RecyclerView, di mana setiap posisi pemain memiliki warna kartu yang berbeda untuk memperjelas peran di lapangan.

Setiap elemen tampilan diatur dengan prinsip Material Design, menghadirkan kesan modern dan bersih. Selain itu, aplikasi juga menggunakan BottomSheetDialog untuk menampilkan detail pemain secara ringkas dan interaktif tanpa harus berpindah halaman.

Dengan kombinasi antara teknologi API modern, arsitektur aplikasi yang efisien, dan tampilan antarmuka yang menarik, aplikasi ini tidak hanya berfungsi sebagai sarana informasi, tetapi juga sebagai contoh implementasi nyata pengembangan aplikasi Android berbasis data dinamis.

Secara keseluruhan, Responsi 1 Mobile – Charlton Athletic FC merepresentasikan integrasi yang solid antara data layer, logic layer, dan UI layer dalam satu sistem yang mudah dipelihara, cepat direspons, serta menyuguhkan pengalaman pengguna yang menyenangkan dan profesional.


Penjelasan Alur 


---

## **Penjelasan Alur Proses Aplikasi "Responsi 1 Mobile – Charlton Athletic FC"**

Aplikasi *Responsi 1 Mobile – Charlton Athletic FC* dirancang untuk menampilkan informasi lengkap tentang klub sepak bola Charlton Athletic FC secara real-time dengan memanfaatkan API publik dari **Football-Data.org**. Aplikasi ini dibuat menggunakan bahasa **Kotlin**, dengan dukungan **Retrofit** sebagai library komunikasi jaringan dan **Kotlin Coroutines** untuk pengelolaan proses asynchronous.

### **1. Inisialisasi dan Pemanggilan API**

Ketika aplikasi pertama kali dijalankan, kelas **`MainActivity`** berperan sebagai titik masuk utama (*entry point*). Di dalam fungsi `onCreate()`, terdapat pemanggilan terhadap fungsi `loadTeamData()` yang bertugas untuk memuat data klub dari server. Fungsi ini memanggil endpoint utama Football-Data.org yang berada di alamat `https://api.football-data.org/v4/`, dengan parameter `teamId` yang sesuai dengan ID klub Charlton Athletic FC.

Pemanggilan API dilakukan melalui objek `RetrofitInstance`, yang telah dikonfigurasi untuk memiliki *base URL*, *GSON ConverterFactory*, dan antarmuka komunikasi (`FootballDataApi`) yang berisi definisi fungsi-fungsi endpoint. Pada fungsi `getTeam(id: Int)`, setiap permintaan ke server akan menyertakan *header* khusus berisi `X-Auth-Token`, yaitu API Key resmi dari Football-Data.org agar sistem dapat mengakses data yang bersifat terbatas.

### **2. Arsitektur dan Pengelolaan Thread**

Untuk memastikan bahwa proses permintaan data tidak menghambat interaksi pengguna, semua *network call* dilakukan secara asynchronous menggunakan **Kotlin Coroutines**. Pemanggilan API dilakukan di dalam `CoroutineScope(Dispatchers.IO)` agar dijalankan pada *background thread*, sehingga UI tetap responsif. Setelah data diterima dari server, hasilnya diproses kembali ke *main thread* menggunakan `withContext(Dispatchers.Main)` agar elemen-elemen UI dapat diperbarui secara langsung.

Pendekatan ini memberikan keuntungan besar dalam hal efisiensi dan performa, karena pengguna tidak akan mengalami lag atau *freezing* meskipun jaringan sedang lambat. Selain itu, coroutine juga membuat kode lebih mudah dibaca dibandingkan pendekatan *callback* tradisional.

### **3. Proses Konversi dan Pemodelan Data**

Setelah respons berhasil diterima, **Retrofit** secara otomatis memetakan data dalam format JSON ke dalam objek model **`TeamResponse`** melalui bantuan *GSON ConverterFactory*.
Objek `TeamResponse` ini berisi berbagai atribut penting seperti:

* **name** → nama resmi klub,
* **crest** → URL logo klub,
* **founded**, **venue**, dan **tla** → informasi umum klub,
* **coach** → objek yang berisi data pelatih utama, dan
* **squad** → daftar lengkap pemain dalam bentuk list dari `SquadMemberResponse`.

Dengan pendekatan ini, proses parsing JSON menjadi lebih ringkas dan aman karena tipe data sudah ditentukan dalam kelas model.

### **4. Penyajian Data di Antarmuka (UI Layer)**

Data yang diterima dari server kemudian diolah dan ditampilkan ke pengguna melalui tiga halaman utama:

1. **MainActivity** — berfungsi sebagai beranda utama aplikasi, menampilkan nama dan logo klub serta menyediakan navigasi ke halaman lain melalui komponen *menu card*.
2. **HalamanClubActivity** — menyajikan profil, sejarah, dan informasi umum klub. Data ditampilkan dalam format teks deskriptif yang diambil dari hasil parsing objek `TeamResponse`.
3. **HalamanCoachActivity** — menampilkan data pelatih utama seperti nama lengkap, tanggal lahir, kebangsaan, dan pengalaman melatih. Tampilan di halaman ini ditingkatkan dengan efek *fade-in animation* yang ditampilkan setelah penundaan selama tiga detik menggunakan `Handler().postDelayed`, agar pengguna merasakan pengalaman visual yang lebih menarik dan dinamis.
4. **HalamanTeamActivity** — menampilkan seluruh daftar pemain dari klub dalam bentuk *RecyclerView* menggunakan `PlayerAdapter`.

### **5. Mekanisme RecyclerView dan Pewarnaan Dinamis**

Di **`PlayerAdapter`**, setiap data pemain (`SquadMemberResponse`) diikat ke tampilan kartu (`MaterialCardView`) yang menampilkan nama dan kewarganegaraan pemain. Di dalam fungsi `onBindViewHolder()`, terdapat mekanisme *conditional coloring* menggunakan `when expression`, yang mengubah warna latar belakang kartu berdasarkan posisi pemain di lapangan:

* **Goalkeeper** → kuning (#FFDE59),
* **Defender** → biru (#598BFF),
* **Midfielder** → hijau (#7DDA58),
* **Forward** → merah (#E4080A).

Pendekatan ini tidak hanya memperjelas klasifikasi peran tiap pemain, tetapi juga memberikan elemen visual yang menarik dan informatif secara cepat bagi pengguna.

Selain itu, ketika pengguna mengetuk kartu pemain, muncul **BottomSheetDialog** yang menampilkan detail lengkap pemain, seperti tanggal lahir, posisi, dan kebangsaan. Dialog ini memberikan pengalaman interaktif tanpa perlu berpindah halaman.

### **6. Integrasi dan Pengalaman Pengguna**

Secara keseluruhan, aplikasi ini mengintegrasikan **lapisan data (data layer)**, **lapisan logika (logic layer)**, dan **lapisan tampilan (UI layer)** secara kohesif.

* Lapisan *data* diwakili oleh `RetrofitInstance` dan model data (`TeamResponse`, `SquadMemberResponse`).
* Lapisan *logika* dikelola oleh setiap `Activity` yang menangani pemanggilan API dan pemrosesan hasil.
* Lapisan *tampilan* menampilkan data melalui *RecyclerView*, *CardView*, dan *BottomSheetDialog* yang responsif dan intuitif.

Seluruh alur aplikasi ini dirancang mengikuti prinsip **modern Android development**, dengan memperhatikan efisiensi, keterbacaan kode, dan kenyamanan pengguna. Pemisahan tanggung jawab antar komponen juga membuat aplikasi mudah dikembangkan lebih lanjut — misalnya dengan menambahkan fitur *search player*, *team comparison*, atau *real-time match data* di masa mendatang.

### **7. Kesimpulan**

Dengan memanfaatkan teknologi **Retrofit**, **Kotlin Coroutines**, dan **Material Design Components**, aplikasi “Responsi 1 Mobile – Charlton Athletic FC” berhasil mengimplementasikan arsitektur modern yang efisien, interaktif, dan mudah dipelihara. Seluruh proses — mulai dari pemanggilan API, parsing data JSON, hingga penyajian hasil dalam antarmuka visual — berjalan secara terstruktur dan responsif. Hasil akhirnya adalah aplikasi mobile yang tidak hanya informatif tetapi juga estetis, dengan pengalaman pengguna yang ringan dan konsisten dengan standar pengembangan aplikasi Android profesional.


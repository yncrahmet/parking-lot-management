# 🅿️ ParkingLot Management 

ParkingLot, mikroservis mimarisiyle geliştirilmiş bir **otopark rezervasyon sistemidir**.  
Kullanıcılar mevcut otopark alanlarını görüntüleyebilir, rezervasyon yapabilir ve ödeme işlemlerini güvenli bir şekilde tamamlayabilir. Sistem, servisler arası iletişimde Kafka ve Feign Client gibi modern teknolojilerden faydalanır.

---

## 🛠️ Kullanılan Teknolojiler

| Teknoloji           | Açıklama                                    |
|---------------------|---------------------------------------------|
| Java 17, 21         | Backend dili                                |
| Spring Boot         | Ana uygulama çatısı                         |
| Spring Cloud Gateway| API yönlendirme ve global güvenlik katmanı  |
| Spring Security     | Kimlik doğrulama ve yetkilendirme           |
| Keycloak            | Merkezi kimlik yönetimi (OAuth2)     |
| OpenFeign           | Servisler arası REST haberleşmesi           |
| Kafka               | Asenkron mesajlaşma                         |
| Stripe API          | Ödeme işlemleri                             |
| PostgreSQL          | Veritabanı                                  |
| Redis               | Cache / Session yönetimi                    |
| Lombok              | Boilerplate kodlardan kurtulmak için        |

---

## 🔍 Mikroservisler

### 1. **Gateway Server**
- Sistem giriş noktasıdır.
- Tüm mikroservislerin dış dünyaya açılan kapısıdır.
- Güvenlik, token doğrulama ve yetkilendirme burada yapılır (Keycloak entegrasyonu).

### 2. **Config Server**
- Tüm servislerin `application.yml` gibi yapılandırma dosyaları burada merkezi olarak tutulur.
- Servisler ilk açılışta konfigürasyonlarını buradan çeker.

### 3. **Eureka Server**
- Servislerin birbirini dinamik olarak bulmasını sağlar.
- Load balancing & failover gibi işlemler için kullanılır.

### 4. **Admin Server**
- Sisteme özel admin panel veya monitoring aracı olarak çalışır.
- Spring Boot Admin üzerinden servislerin sağlık durumu vs. izlenebilir.

### 5. **Parking Service**
- Kullanıcı ve otopark yönetimi buradadır.
- Kullanıcı CRUD işlemleri, otopark ekleme/düzenleme/silme burada yapılır.
- Konum bazlı otopark listeleme desteği içerir.

### 6. **Parking Reservation Service**
- Kullanıcılar otoparklara rezervasyon yapabilir.
- Kapasite kontrolü yapılır.
- Kafka ile ödeme ve bildirim servislerine mesaj gönderilir.

### 7. **Payment Service**
- Stripe API ile ödeme işlemleri burada gerçekleştirilir.
- Kafka üzerinden başarılı/başarısız işlemler bildirilir.

### 8. **Stripe Service**
- Stripe işlemlerini yöneten alt servis. Ayrı konumlandırılmıştır.

### 9. **Email Service**
- Kafka'dan gelen mesajlara göre e-posta bildirimi yapar.
- Rezervasyon onayı, ödeme sonucu gibi otomatik bildirimler gönderilir.

### 10. **Report Generation Service**
- Admin'ler için sistem istatistikleri sağlar.
  - Rezervasyon sayıları
  - Finansal özetler
  - Otopark doluluk oranları

---

## 🚀 Kurulum

### 1. Projeyi Klonlayın

```bash
git clone https://github.com/yncrahmet/parking-lot-management.git
cd parking-lot-management
```

### 2. Veritabanı Yapılandırması

`application.yml` dosyalarındaki bilgileri değiştir:

```yaml
spring:
  datasource:
    url: jdbc:postgresql://localhost:5432/parkingdb
    username: postgres
    password: yourpassword
```

### 3. Keycloak Kurulumu

- Yeni realm oluştur: `parking-lot-realm`
- Her servis için bir client tanımla
- `USER`, `ADMIN` rollerini oluştur
- Kullanıcıları ve rollerini atayıp yapılandır

### 4. Stripe API Entegrasyonu

- Stripe test hesabı oluştur
- `application.yml` dosyasına şu şekilde ekle:

```yaml
stripe:
  api-key: your_stripe_secret_key
```

---

## 📡 Servisler Arası İletişim

- **Feign Client** üzerinden RESTful haberleşme yapılır.
- **Kafka** üzerinden servisler arasında event bazlı mesajlaşma sağlanır.
- Swagger ile her servise özel API dokümantasyonu (isteğe bağlı) eklenebilir.

---

## 🧪 Test ve Debug Süreci

- Unit testler için `JUnit` ve `Mockito` kullanılabilir.
- Swagger ve Postman ile manuel test için REST endpoint’ler test edilebilir.
- `mvn test` ile CLI üzerinden tüm servislerde test çalıştırılabilir.

---

## 📁 Daha Fazlası

👉 Projenin tüm kaynak kodu:  
https://github.com/yncrahmet/parking-lot-management

---

## ✨ Katkı Sağlamak İstersen

Pull request göndermekten çekinme.  
Yeni özellik ekleme, bug fix veya dokümantasyon katkıları değerlidir.

---

## 📫 İletişim

Herhangi bir soru ya da önerin varsa:  
📧 [Linkedin Hesabım](https://www.linkedin.com/in/yncrahmet)

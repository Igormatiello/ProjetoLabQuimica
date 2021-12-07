call mvnw clean package -DskipTests spring-boot:repackage
docker build -t utfprpatobranco/oficina_lab_quimica_backend:1.0.0 .
docker push utfprpatobranco/oficina_lab_quimica_backend:1.0.0
pause
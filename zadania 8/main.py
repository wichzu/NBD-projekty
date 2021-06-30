import riak
riakClient = riak.RiakClient(pb_port=8087)

bucket = riakClient.bucket('s23707')

# Napisz program, który wrzuci do bazy dokument

studentka = {"name": "Zuzanna", "surname":"Wicher", 'studentka': True}
key = bucket.new('studentka', data=studentka)
key.store()
print('Dodano studentke')


# pobierze go i wypisze

pobranaStudentka = bucket.get('studentka')
print('Dane studentki: ' + str(pobranaStudentka.data))


# zmodyfikuje go

pobranaStudentka.data['studentka'] = False
pobranaStudentka.store()


# następnie pobierze i wypisze

pobranaStudentka2 = bucket.get('studentka')
print('Co tu sie zadzialo: ' + str(retrievedStudent2.data))


# a na końcu usunie go i spróbuje pobrać z bazy

pobranaStudentka2.delete()
print("Studentka zostala usunieta, tylko co teraz z pobraniem")
pobranaStudentka3 = bucket.get('studentka')
print('Czy nadal mozna ja pobrac: ' + str(pobranaStudentka3.data))
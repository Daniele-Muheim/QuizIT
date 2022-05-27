# QuizIT

Kotlin App Modul Mobpro

<h2>Technische Anforderungen</h2>
<h4>Kommunikation über HTTP: </h4>
- REST-Requests
- Laden von Leaderboard, Fragekatalog, senden von neuem Score
<br><br>
<h4>Lokale Persistenz mittels DefaultSharedPreference:</h4>
- Speichern des Benutzernamens, letzter Sync des Leaderboards
<br><br>
<h4>Lokale Persistenz mittels Datenbank:</h4>
- RoomDB, zwischenspeichern von Leaderboard
- Letzter Stand der Bestenliste weiterhin einsehbar
<br><br>
<h4>Implementierung & Verwendung eigener Server-Komponente: </h4>
- REST-API (node.js) & MongoDB
- Neue Scores erhalten und serverseitig in MongoDB persistiert
- https://github.com/dave1b/QuizIT-Backend
<br><br>
<h2>Features</h2>
<li>Eigenen Benutzernamen für das Quiz angeben</li>
<li>Feedback zur gewählten Frage erhalten</li>
<li>Leaderboard mit zentralen Datenspeicherung</li>
<li>Laden von dynamischem Fragenkataloge</li>
<li>Offlinefähigkeit des Leaderboards</li>
<li>Anzeigen des Scores am Ende des Durchganges</li>

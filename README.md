# QuizIT

Kotlin App Modul Mobpro

<h2>Technische Anforderungen</h2>
<h4>Kommunikation über HTTP: </h4>
<li>REST-Requests</li>
<li>Laden von Leaderboard, Fragekatalog, senden von neuem Score</li>

<h4>Lokale Persistenz mittels DefaultSharedPreference:</h4>
<li>Speichern des Benutzernamens, letzter Sync des Leaderboards</li>

<h4>Lokale Persistenz mittels Datenbank:</h4>
<li>RoomDB, zwischenspeichern von Leaderboard</li>
<li>Letzter Stand der Bestenliste weiterhin einsehbar</li>

<h4>Implementierung & Verwendung eigener Server-Komponente: </h4>
<li>REST-API (node.js) & MongoDB</li>
<li>Neue Scores erhalten und serverseitig in MongoDB persistiert</li>


<h2>Features</h2>
<li>Eigenen Benutzernamen für das Quiz angeben</li>
<li>Feedback zur gewählten Frage erhalten</li>
<li>Leaderboard mit zentralen Datenspeicherung</li>
<li>Laden von dynamischem Fragenkataloge</li>
<li>Offlinefähigkeit des Leaderboards</li>
<li>Anzeigen des Scores am Ende des Durchganges</li>

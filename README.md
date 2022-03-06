
## API Reference


#### Get All Match Info
```http
  GET /match/
```
#### Get Match Info By Id
```http
  GET /match/{matchId}
```
#### Get Team Stats of Match
```http
  GET /match/{matchId}/teams
```
#### Play New Match
```http
  POST /match/new
```

###
#### Get All Teams info
```http
  GET /team/
```
#### Get Teams info by Id
```http
 GET /team/{teamId}
```
#### Get PlayersInfo Of Team
```http
  GET /team/{teamId}/player
```
#### Create New Team
```http
  POST team/new
```

###
#### Get All Player info
```http
  GET /player/
```
#### Get Teams info by Id
```http
 GET /player/{playerId}
```
#### Get PlayerStats Of Match
```http
 GET /player/{playerId}/{matchId}
```
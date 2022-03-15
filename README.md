# Cricket_Game
## API Reference
### Match APIs
#### Get All Match Info
```http
  GET /match/
```
#### Get Match Info By ID
```http
  GET /match/{matchId}
```
#### Play New Match
```http
  POST /match/new/{overInInning}
```
#### Get Team Stats of Match
```http
  GET /match/{matchId}/teams
```
#### Delete Match Details
```http
  DELETE /match/{matchId}
```

### Team APIs
#### Get All Teams info
```http
  GET /team/
```
#### Get Teams info by ID
```http
 GET /team/{teamId}
```
#### Get PlayersInfo Of Team
```http
  GET /team/{teamId}/player
```
#### Create New Team
```http
  POST /team/new
```
#### Update Team Name
```http
  PUT /team/update
```

### Player APIs
#### Get All Player info
```http
  GET /player/
```
#### Get Teams info by ID
```http
 GET /player/{playerId}
```
#### Get PlayerStats Of Match
```http
 GET /player/{playerId}/{matchId}
```
#### Update Player Name
```http
  PUT /player/update
```

### Over API
#### Get Over Stats In Match
```http
 GET /over/{matchId}
```
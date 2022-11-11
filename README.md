# Kwetter Backend

## Endpoints

De volgende API's zijn beschikbaar in de applicatie

### User-service

#### User-controller

| Path      | Method | Description                           |
| --------- | :----: | ------------------------------------- |
| "/"       |  GET   | Krijg info over de standaard gebuiker |
| "/new"    |  POST  | Maak een nieuwe gebruiker             |
| "/update" |  POST  | Update een gebruiker                  |
| "/{id}"   | DELETE | Verwijder een gebruiker               |

### Message-service

#### Message-controller

| Path      | Method | Description                      |
| --------- | :----: | -------------------------------- |
| "/"       |  GET   | Krijg het standaard test bericht |
| "/new"    |  POST  | Maak een nieuw bericht           |
| "/update" |  POST  | Update een bericht               |
| "/{id}"   | DELETE | Verwijder een bericht            |

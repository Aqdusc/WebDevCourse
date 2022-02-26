import {Component, ElementRef, OnInit, ViewChild} from '@angular/core';
import {HttpClient} from '@angular/common/http';

@Component({
  selector: 'app-search-recipe',
  templateUrl: './search-recipe.component.html',
  styleUrls: ['./search-recipe.component.css']
})
export class SearchRecipeComponent implements OnInit {
  @ViewChild('recipe') recipes: ElementRef;
  @ViewChild('place') places: ElementRef;
  recipeValue: any;
  placeValue: any;
  venueList = [];
  recipeList = [];
  noRecords: boolean = true;
  currentLat: any;
  currentLong: any;
  geolocationPosition: any;

  constructor(private _http: HttpClient) {
  }

  ngOnInit() {

    window.navigator.geolocation.getCurrentPosition(
      position => {
        this.geolocationPosition = position;
        this.currentLat = position.coords.latitude;
        this.currentLong = position.coords.longitude;
      });
  }

  getVenues() {

    this.recipeValue = this.recipes.nativeElement.value;
    this.placeValue = this.places.nativeElement.value;

    if (this.recipeValue !== null) {
      this._http.get('https://api.edamam.com/search?q=' + this.recipeValue +
        '&app_id=1c6d8e3e&app_key=0e6901ec822aae1068fadb076e227e1c&from=0&to=10&calories=591-722&health=alcohol-free').subscribe((recipes: any) => {
          console.log(this.noRecords)
          this.recipeList = Object.keys(recipes.hits).map((rec,index) =>  {
            const recipe = recipes.hits[index].recipe;
            return { name: recipe.label, content: recipe.digest[0].schemaOrgTag, icon: recipe.image, add: recipe.address, url: recipe.url }
          });
        }
        );
    }

    if (this.placeValue != null && this.placeValue !== '' && this.recipeValue != null && this.recipeValue !== '') {
      this._http.get('https://api.foursquare.com/v2/venues/search?client_id=4SAMIUR3J0XSCUTOY2Z0V5EH5R2TSEZDQ002FQ5X30VW0QXP' +
        '&client_secret=FRAYZUHGRZF2TGZM5WEE5JMEQBAZOA2KZVZZTBBMRDV5ZF0C&v=20220225&limit=10&near=' + this.placeValue + '&query=' + this.recipeValue).subscribe((restaurants: any) => {
          this.venueList = Object.keys(restaurants.response.venues).map((input,index) => {
            const restaurant = restaurants.response.venues[index];
            console.log(restaurant)
            return { name: restaurant.name, location: restaurant.location };

          })
        });
    }
  }
}

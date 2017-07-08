import {Component, OnInit} from '@angular/core';
import {FixturesService} from "../../services/fixtures.service";
import {Fixture} from "../../interfaces/fixture";
declare var jQuery: any;

@Component({
  selector: 'home-page',
  templateUrl: './home-page.component.html',
  styleUrls: ['./home-page.component.scss']
})
export class HomePageComponent implements OnInit {

  fixtures: Fixture[] = [];
  filteredFixtures: Fixture[] = [];
  shouldAnimate: boolean = false;

  constructor(private fixturesService: FixturesService) {
  }

  ngOnInit() {

    this.fixturesService.getUpcomingFixtures('1')
      .subscribe(fixtures => {
        this.fixtures = fixtures;
        this.filteredFixtures = fixtures;
        let adaptedFixtures = this.adaptFixturesToSearchInputFormat(this.fixtures);

        jQuery('.ui.search')
          .search({
            source: adaptedFixtures,
            showNoResults: true,
            transition: 'fade',
            maxResults: 3,
            onSelect: (response) => {
              this.filterFixturesOnSelection(response.title);
            },
            onSearchQuery: (response) => {
              response.length > 1 ? this.filterFixturesOnSubstring(response) : this.resetFixturesToOriginalState();
            }
          });
      });

  }

  onKey(event: KeyboardEvent) {
    if ((<HTMLInputElement>event.target).value === '') {
      this.resetFixturesToOriginalState();
    }
  }

  private adaptFixturesToSearchInputFormat(fixtures: Fixture[]): string[] {

    let adaptedFixtures = new Set();
    let searchResults = [];

    fixtures.forEach(fixture => {
      adaptedFixtures.add(fixture.countryName);
      adaptedFixtures.add(fixture.awayTeam);
      adaptedFixtures.add(fixture.homeTeam);
    });

    adaptedFixtures.forEach(fixture => {
      searchResults.push({title: fixture});
    });

    return searchResults;
  }

  private filterFixturesOnSubstring(searchResults: string) {
    let stringToSearch = searchResults.toLowerCase();

    this.filteredFixtures = this.fixtures.filter(fixture => {
      return fixture.homeTeam.toLowerCase().includes(stringToSearch) ||
        fixture.awayTeam.toLowerCase().includes(stringToSearch) ||
        fixture.countryName.toLowerCase().includes(stringToSearch)
    });
  }

  private filterFixturesOnSelection(selection: string) {
    this.filteredFixtures = this.fixtures.filter(fixture => {
      return fixture.homeTeam === selection || fixture.awayTeam === selection || fixture.countryName === selection;
    })
  }

  private resetFixturesToOriginalState(): void {
    this.filteredFixtures = this.fixtures;
  }

}

import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { LeagueMainPageComponent } from './league-main-page.component';

describe('LeagueMainPageComponent', () => {
  let component: LeagueMainPageComponent;
  let fixture: ComponentFixture<LeagueMainPageComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ LeagueMainPageComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(LeagueMainPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should be created', () => {
    expect(component).toBeTruthy();
  });
});

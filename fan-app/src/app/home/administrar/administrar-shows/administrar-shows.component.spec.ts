import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AdministrarShowsComponent } from './administrar-shows.component';

describe('AdministrarShowsComponent', () => {
  let component: AdministrarShowsComponent;
  let fixture: ComponentFixture<AdministrarShowsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AdministrarShowsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AdministrarShowsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

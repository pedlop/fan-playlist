import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AdministrarArtistasComponent } from './administrar-artistas.component';

describe('AdministrarArtistasComponent', () => {
  let component: AdministrarArtistasComponent;
  let fixture: ComponentFixture<AdministrarArtistasComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AdministrarArtistasComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AdministrarArtistasComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

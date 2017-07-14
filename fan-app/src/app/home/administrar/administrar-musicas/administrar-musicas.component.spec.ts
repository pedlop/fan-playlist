import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AdministrarMusicasComponent } from './administrar-musicas.component';

describe('AdministrarMusicasComponent', () => {
  let component: AdministrarMusicasComponent;
  let fixture: ComponentFixture<AdministrarMusicasComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AdministrarMusicasComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AdministrarMusicasComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

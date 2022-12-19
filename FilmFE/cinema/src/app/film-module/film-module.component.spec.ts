import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { FilmModuleComponent } from './film-module.component';

describe('FilmModuleComponent', () => {
  let component: FilmModuleComponent;
  let fixture: ComponentFixture<FilmModuleComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ FilmModuleComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(FilmModuleComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

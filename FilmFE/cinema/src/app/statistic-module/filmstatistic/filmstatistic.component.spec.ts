import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { FilmstatisticComponent } from './filmstatistic.component';

describe('FilmstatisticComponent', () => {
  let component: FilmstatisticComponent;
  let fixture: ComponentFixture<FilmstatisticComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ FilmstatisticComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(FilmstatisticComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

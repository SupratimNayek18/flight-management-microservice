import { ComponentFixture, TestBed } from '@angular/core/testing';

import { NoticesPromotionsComponent } from './notices-promotions.component';

describe('NoticesPromotionsComponent', () => {
  let component: NoticesPromotionsComponent;
  let fixture: ComponentFixture<NoticesPromotionsComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [NoticesPromotionsComponent]
    });
    fixture = TestBed.createComponent(NoticesPromotionsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

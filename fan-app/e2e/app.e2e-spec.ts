import { FanAppPage } from './app.po';

describe('fan-app App', () => {
  let page: FanAppPage;

  beforeEach(() => {
    page = new FanAppPage();
  });

  it('should display message saying app works', () => {
    page.navigateTo();
    expect(page.getParagraphText()).toEqual('app works!');
  });
});

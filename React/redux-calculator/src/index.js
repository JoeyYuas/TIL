import React from 'react';
import { render } from 'react-dom';
import CalculatorContainer from './containers/CalculatorContainer';
import { createStore } from 'redux';
import { Provider } from 'react-redux';
import reducer from './reducers';

// Reactはまず、src直下のindex.jsを見に行く

// ①storeの宣言と初期化(Storeとは、「状態」を管理する入れ物みたいなものを示す)
// この時点で一度、./reducersを見に行っている

// ④ここで、storeには初期化されているinitialAppStateが返される
const store = createStore(reducer);

// ⑤そしてrender（viewに反映）する。CalculatorContainerをrenderしているため、見に行く。
render(
  <Provider store={store}>
    <CalculatorContainer />
  </Provider>,
  document.getElementById('root')
);



// import React from 'react';
// import ReactDOM from 'react-dom';
// import CalculatorContainer from './containers/CalculatorContainer';
//
// ReactDOM.render(
//   <CalculatorContainer />,
//   document.getElementById('root')
// );

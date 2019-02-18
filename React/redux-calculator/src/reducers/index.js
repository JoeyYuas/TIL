import { combineReducers } from 'redux';
import calculator from './calculator';

//②すると、calculatorを見に行っているので、見に行く。
const reducer = combineReducers({
  calculator,
});

export default reducer;

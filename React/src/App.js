/*
import MyComponent from "./MyComponent";

const App = () =>{
  return <MyComponent favoriteNumber={1}>리액트</MyComponent>;
};

export default App;
*/

/*
import Counter from './Counter';
const App = () =>{
  return <Counter />
};
export default App;
*/

/*
import Say from './Say';
const App = () => {
  return <Say />
};
export default App;
*/

/*
import EventPractice from './EventPractice';
const App = () => {
  return <EventPractice />
};
export default App;
*/

/*
import { Component } from "react";
import ValidationSample from "./ValidationSample";
class App extends Component {
  render(){
    return (
      <ValidationSample/>
    );
  }
}
export default App;
*/

/*
import { Component } from "react";
import ScrollBox from "./ScrollBox";
class App extends Component {
  render(){
    return (
      <div>
        <ScrollBox ref={(ref) => this.ScrollBox=ref} />
        <button onClick={() => this.ScrollBox.scrollToBottom()}>맨밑으로</button>
      </div>
    );
  }
}
export default App;
*/

import { Component } from "react";
import InteractionSample2 from "./InteractionSample2";
class App extends Component {
  render(){
    return (
      <InteractionSample2/>
    );
  }
}
export default App;
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

/*
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
*/

/*
import { Component } from "react";
import LifeCycleSample from "./LifeCycleSample";
function getRandomColor(){
  return '#' + Math.floor(Math.random() * 16777215).toString(16);
}
class App extends Component{
  state = { color : '#000000' }

  handleClick = () => {
    this.setState({
      color: getRandomColor()
    });
  }

  render() {
    return(
      <div>
        <button onClick={this.handleClick}>랜덤 생상</button>
        <LifeCycleSample color = {this.state.color}/>
      </div>
    );
  }
}
export default App;
*/

/*
import Info from './info';
const App = () => {
  return <Info />;
};
export default App;
*/

/*
import Counter3 from './Counter2';
const App = () =>{
  return <Counter3 />
};
export default App;
*/

/*
import { useState } from 'react';
import Info from './info';
const App = () => {
  const [visible, setVisible] = useState(false);
  return (
    <div>
      <button
        onClick={() => {
          setVisible(!visible);
        }}
        >
          {visible ? '숨기기' : '보이기'}
        </button>
        <hr/>
        {visible && <Info/>}
    </div>
  )
};
export default App;
*/


import Info3 from './info3';
const App = () =>{
  return <Info3 />
};
export default App;


/*
import Average from './Average';
const App = () =>{
  return <Average />
};
export default App;
*/
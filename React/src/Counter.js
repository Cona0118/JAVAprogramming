import { Component } from 'react';

class Counter extends Component{
    // constructor(props) {
    //     super(props); // 초깃값 설정 방법 1
    //     this.state = {
    //         number:0,
    //         fixedNumber:0
    //     };
    // }

    state ={   // 초깃값 설정 방법 2
        number:0,
        fixedNumber:0
    };

    render() {
    const {number, fixedNumber} = this.state; 
    return (
        <div>
            <h1>{number}</h1>
            <h2>바뀌지 않는 값: {fixedNumber}</h2>
            <button 
                onClick = {() => { // Onclick 시 호출될 함수
                    this.setState({number:number+1}); // this.setState를 사용하여 state에 새 값 지정
                    this.setState({number:this.state.number+1}); // 비동기 처리되기 때문에 여전히 클릭 한번에 1씩 증가함
                }}
            >
                +1
            </button>
            
            <button
                onClick = {() => {
                    this.setState(prevState => {
                        return {
                            number: prevState.number + 1
                        };
                    });
                    this.setState(prevState => ({ // 위 코드와 같은 기능
                        number: prevState.number + 1 // 함수에서 바로 객체 반환
                    }));
                }}
            >
                +2
            </button>

            <button
                onClick = {() => {
                    this.setState(
                        {
                            number: number + 1
                        },
                        () => { // setState 작업 이후 호출
                            console.log('setState 호출');
                            console.log(this.state);
                        }
                    );
                }}
            >
            +1 & 로그출력
            </button>

            <button 
                onClick = {() => {
                    this.setState({number:0});
                }}
            >
            초기화
            </button>
        </div>
    )
    }
}



export default Counter;
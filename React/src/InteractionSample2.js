import { useState } from "react";

const InteractionSample2 = () => {
    const [names, setNames] = useState([
        {id: 1, text: '봄'},
        {id: 2, text: '여름'},
        {id: 3, text: '가을'},
        {id: 4, text: '겨울'}
    ]);
    const [inputText, setInputText] = useState('');
    const [nextId, setNextId] = useState(5); // 새로운 항목 추가 시 사용할 id

    const onChange = e => setInputText(e.target.value);
    const onClick = () => {
        const nextNames = names.concat({
            id: nextId, // nextId 값을 id로 설정
            text: inputText
        });
        setNextId(nextId + 1); // 다음 nextId 값 1증가
        setNames(nextNames); // names 값 업데이트
        setInputText(''); // input 초기화
    };
    const onRemove = id => {
        const nextNames = names.filter(name => name.id !== id);
        setNames(nextNames);
    };

    const nameList = names.map(name => (
        <li key={name.id} onDoubleClick={() => onRemove(name.id)}> {name.text}</li>
    ));

    return (
        <>
        <input value={inputText} onChange={onChange} />
        <button onClick={onClick}>추가</button>
        <ul>{nameList}</ul>
        </>
    );
};

export default InteractionSample2;
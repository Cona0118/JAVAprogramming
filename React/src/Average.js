import { useState, useMemo, useCallback, useRef } from "react";

const getAverage = numbers => {
    console.log('평균값 계산중..');
    if(numbers.length === 0) return 0;
    const sum = numbers.reduce((a,b) => a+b);
    return sum/numbers.length;
};

const Average = () => {
    const [list, setList] = useState([]);
    const [number, setNumber] = useState('');
    const inputEl = useRef(null); // useRef 추가

    /*
    const onChange = e => {
        setNumber(e.target.value);
    };
    const onInsert = () => {
        const nextList = list.concat(parseInt(number));
        setList(nextList);
        setNumber('');
    };
    */

    // usecallback 으로 함수 재사용
    const onChange = useCallback(e => {
        setNumber(e.target.value);
    }, []); // 처음 렌더링 될때만 함수 생성

    const onInsert = useCallback(() => {
        const nextList = list.concat(parseInt(number));
        setList(nextList);
        setNumber('');
        inputEl.current.focus(); 
    }, [number, list]); // number 혹은 list 변경시 함수 행성


    const avg = useMemo(() => getAverage(list), [list]);

    return (
        <div>
            <input value={number} onChange={onChange} ref={inputEl} />
            <button onClick={onInsert}>등록</button>
            <ul>
                {list.map((value, index) => (
                    <li key={index}>{value}</li>
                ))}
            </ul>
            <div>
                <b>평균값: </b> {avg}
            </div>
        </div>
    )
};

export default Average;
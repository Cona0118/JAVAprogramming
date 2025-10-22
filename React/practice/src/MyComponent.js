import PropTypes from 'prop-types';

const MyComponent = (props) => {
    // const name = props.name || '기본이름';
    // const children = props.children || '기본내용';
    const {name = '기본이름', children = '기본내용', favoriteNumber} = props; // 비구조화 할당

    return (
    <div>
        안녕하세요 제이름은 {name} 입니다 <br />
        {children} <br />
        favoriteNumber : {favoriteNumber}
    </div>
    );
};

MyComponent.propTypes = {
    name : PropTypes.string,
    favoriteNumber : PropTypes.number.isRequired
};



export default MyComponent;
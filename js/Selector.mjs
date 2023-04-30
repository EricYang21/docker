export default class Selector extends React.Component {
  constructor(props) {
    super(props);
    // list of bools for state of each checkbox
    const entries = props.entries.map((_name) => false);
    this.state = {
      search: "",
      entries: entries
    };
    this.makeCheckboxHandler = this.makeCheckboxHandler.bind(this);
  }

  makeCheckboxHandler(index) {
    return (event) => {
      this.setState((state, _props) => {
        state.entries[index] = event.target.checked;
        return state;
      });
    };
  }

  render() {
    const checkboxes = this.props.entries
      .map((name, i) => {
        const checked = this.state.entries[i];
        const visible = name.includes(this.state.search);
        const className = visible ? "selector-entry" : "selector-entry-hidden";
        return <label className={className} key={name}>
          <input type="checkbox" checked={checked} onChange={this.makeCheckboxHandler(i)} />
          {name}
        </label>;
      });
    return <div className="selector shadowy-box">
      <div style={{textAlign: "center"}}>
        <input
          type="text"
          className="selector-search"
          placeholder="Search"
          onChange={(event) => this.setState({search: event.target.value})}
        />
      </div>
      {checkboxes}
    </div>;
  }
}

Selector.propTypes = {
  entries: PropTypes.arrayOf(PropTypes.string)
};

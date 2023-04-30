export default class ContractorList extends React.Component {
  constructor(props) {
    super(props);
  }

  render() {
    const entries = this.props.contractors
      .map((c, i) => <ContractorEntry key={i} {...c} />);
    return <div className="contractor-list">
      {entries}
    </div>;
  }
}

// this is used by ContractorList propTypes so it must come first in the file
const contractorEntryPropTypes = {
  name: PropTypes.string,
  phone: PropTypes.string,
  certifications: PropTypes.arrayOf(PropTypes.string)
};

ContractorList.propTypes = {
  contractors: PropTypes.arrayOf(
    PropTypes.shape(contractorEntryPropTypes)
  )
};

class ContractorEntry extends React.Component {
  constructor(props) {
    super(props);
  }

  render() {
    let certifications = null;
    if (this.props.certifications.length == 0) {
      certifications = <span className="certification no-certification">None</span>;
    } else {
      certifications = this.props.certifications
        .map((name, index) => <span key={index} className="certification">{name}<br /></span>);
    }
    return <div className="contractor-entry shadowy-box">
      <div className="contractor-header">
        <span>{this.props.name}</span>
        <span className="contractor-phone">{this.props.phone}</span>
      </div>
      Certifications:
      <div className="certification-list">
        {certifications}
      </div>
    </div>;
  }
}

ContractorEntry.propTypes = contractorEntryPropTypes;

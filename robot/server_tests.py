import json
import os
import server
import unittest


class ServerTestCase(unittest.TestCase):

    def setUp(self):
        server.app.config['TESTING'] = True
        self.app = server.app.test_client()

    def tearDown(self):
        pass

    def authenticate(self, username, password):
        return self.app.post('/authenticate', data=dict(
            username=username,
            password=password,
        ))

    def test_get_config_settings(self):
        r = self.app.get('/')
        assert 'Hello' in r.data

    def test_authentication(self):
        response = self.authenticate('bert', 'test')
        assert response.status == '200 OK'
        data = json.loads(response.data)
        assert data['status'] == 'success'

        response = self.authenticate('hacker', 'password')
        data = json.loads(response.data)
        assert data['status'] == 'failure'
        assert data['message'] == 'Invalid username or password'

if __name__ == '__main__':
    unittest.main()
